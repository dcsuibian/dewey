import { LoginForm, ProFormText } from '@ant-design/pro-components'
import { LockOutlined, MobileOutlined } from '@ant-design/icons'
import { login } from '@/store/modules/session.ts'
import { useState } from 'react'
import { App, Tabs } from 'antd'
import { AppDispatch } from '@/store'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'

type LoginType = 'phone_number_and_password'

export default function Login() {
  const { message } = App.useApp()
  const dispatch = useDispatch<AppDispatch>()
  const navigate = useNavigate()
  const [loginType, setLoginType] = useState<LoginType>('phone_number_and_password')
  const onFinish = async (values: Record<string, string>) => {
    try {
      await dispatch(
        login({
          type: loginType,
          phoneNumber: values.phoneNumber,
          password: values.password,
        }),
      )
      navigate('/')
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
      label: '手机号密码登录',
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
            rules={[
              {
                required: true,
                message: '请输入密码！',
              },
            ]}
          />
        </>
      ),
    },
  ]
  return (
    <>
      <LoginForm logo="/vite.svg" title="Dewey文库管理系统" onFinish={onFinish}>
        <Tabs
          centered
          activeKey={loginType}
          items={items}
          onChange={activeKey => setLoginType(activeKey as LoginType)}
        />
      </LoginForm>
    </>
  )
}
