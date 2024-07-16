import { LoginForm, ProFormCaptcha, ProFormText } from '@ant-design/pro-components'
import { register } from '@/apis/user.ts'
import { LockOutlined, MobileOutlined } from '@ant-design/icons'
import { useState } from 'react'
import { App } from 'antd'
import { sendOtp } from '@/apis/otp.ts'
import { LOGIN_PATHNAME } from '@/config/routes.tsx'
import { useNavigate } from 'react-router-dom'

const phoneNumberPattern = /^1\d{10}$/
export default function Register() {
  const { message } = App.useApp()
  const navigate = useNavigate()
  const [phoneNumber, setPhoneNumber] = useState<string>('')
  const onFinish = async (values: Record<string, string>) => {
    try {
      await register({
        type: 'phone_number_and_verification_code',
        phoneNumber: values.phoneNumber,
        verificationCode: values.verification,
      })
      message.success('注册成功！')
      navigate(LOGIN_PATHNAME)
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
        purpose: 'register',
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
  return (
    <>
      <LoginForm logo="/vite.svg" title="Dewey" subTitle="新用户注册" onFinish={onFinish}>
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
        <a
          style={{
            float: 'right',
            marginRight: '10px',
            marginBottom: '10px',
          }}
          onClick={() => navigate(LOGIN_PATHNAME)}
        >
          登录
        </a>
      </LoginForm>
    </>
  )
}
