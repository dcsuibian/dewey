import { App as AppProvider, ConfigProvider } from 'antd'
import { AppDispatch } from '@/store'
import { useDispatch } from 'react-redux'
import { useEffect } from 'react'
import { RouterProvider } from 'react-router-dom'
import router from '@/router'
import { getSession } from '@/store/modules/session.ts'

function App() {
  const dispatch = useDispatch<AppDispatch>()
  useEffect(() => {
    dispatch(getSession())
  }, [dispatch])
  return <RouterProvider router={router} />
}

export default function Wrapper() {
  return (
    <ConfigProvider>
      <AppProvider>
        <App />
      </AppProvider>
    </ConfigProvider>
  )
}
