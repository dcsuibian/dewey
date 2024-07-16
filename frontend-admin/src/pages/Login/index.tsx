import { LoginForm, ProFormCaptcha, ProFormCheckbox, ProFormText } from '@ant-design/pro-components'
import { LockOutlined, MobileOutlined } from '@ant-design/icons'
import { login } from '@/store/modules/session.ts'
import { useState } from 'react'
import { App, Tabs } from 'antd'
import { AppDispatch } from '@/store'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import type { Login } from '@/types'
import { sendOtp } from '@/apis/otp.ts'
import { HOME_PATHNAME, REGISTER_PATHNAME } from '@/config/routes.tsx'

type LoginType = Login['type']
const phoneNumberPattern = /^1\d{10}$/
export default function Login() {
  const { message } = App.useApp()
  const dispatch = useDispatch<AppDispatch>()
  const navigate = useNavigate()
  const [loginType, setLoginType] = useState<LoginType>('phone_number_and_password')
  const [phoneNumber, setPhoneNumber] = useState<string>('')
  const onFinish = async (values: Record<string, string>) => {
    try {
      await dispatch(
        login({
          type: loginType,
          phoneNumber: values.phoneNumber,
          password: values.password,
          verificationCode: values.verificationCode,
        }),
      )
      navigate(HOME_PATHNAME)
    } catch (err: unknown) {
      if (err instanceof Error) {
        message.error(err.message)
      } else {
        console.error(err)
      }
    }
  }

  const onGetCaptcha = async () => {
    if (!phoneNumber.match(phoneNumberPattern)) {
      message.error('手机号格式错误！')
      throw new Error('手机号格式错误！')
    }
    try {
      await sendOtp({
        type: 'sms',
        receiver: phoneNumber,
        purpose: 'login',
      })
      message.success('验证码发送成功！')
    } catch (err: unknown) {
      if (err instanceof Error) {
        message.error(err.message)
      } else {
        console.error(err)
      }
    }
  }

  const items = [
    {
      key: 'phone_number_and_password',
      label: '账号密码登录',
      children: (
        <>
          <ProFormText
            name="phoneNumber"
            fieldProps={{
              size: 'large',
              prefix: <MobileOutlined />,
            }}
            placeholder={'手机号'}
            rules={[
              {
                required: true,
                message: '请输入手机号！',
              },
            ]}
          />
          <ProFormText.Password
            name="password"
            fieldProps={{
              size: 'large',
              prefix: <LockOutlined />,
            }}
            placeholder={'密码'}
            rules={[]}
          />
        </>
      ),
    },
    {
      key: 'phone_number_and_verification_code',
      label: '手机验证码登录',
      children: (
        <>
          <ProFormText
            fieldProps={{
              size: 'large',
              prefix: <MobileOutlined />,
              onChange: e => setPhoneNumber(e.target.value),
            }}
            name="phoneNumber"
            placeholder={'手机号'}
            rules={[
              {
                required: true,
                message: '请输入手机号！',
              },
              {
                pattern: phoneNumberPattern,
                message: '手机号格式错误！',
              },
            ]}
          />
          <ProFormCaptcha
            fieldProps={{
              size: 'large',
              prefix: <LockOutlined />,
            }}
            captchaProps={{
              size: 'large',
            }}
            placeholder={'请输入验证码'}
            name="verificationCode"
            rules={[
              {
                required: true,
                message: '请输入验证码！',
              },
            ]}
            onGetCaptcha={onGetCaptcha}
          />
        </>
      ),
    },
  ]
  return (
    <>
      <LoginForm logo="/vite.svg" title="Dewey" subTitle="Dewey文库管理系统" onFinish={onFinish}>
        <Tabs
          centered
          activeKey={loginType}
          items={items}
          onChange={activeKey => setLoginType(activeKey as LoginType)}
        />
        <div
          style={{
            marginBlockEnd: 24,
          }}
        >
          <ProFormCheckbox noStyle name="autoLogin">
            自动登录
          </ProFormCheckbox>
          <a
            style={{
              float: 'right',
              marginLeft: '10px',
            }}
            onClick={() => navigate(REGISTER_PATHNAME)}
          >
            注册
          </a>
          <a
            style={{
              float: 'right',
            }}
          >
            忘记密码
          </a>
        </div>
      </LoginForm>
    </>
  )
}
