import React, { useEffect } from 'react'
import { RootState } from '@/store'
import { useSelector } from 'react-redux'
import { useLocation, useNavigate } from 'react-router-dom'
import { HOME_PATHNAME, LOGIN_PATHNAME } from '@/config/routes.tsx'
import Loading from '@/components/Loading'

interface RouteGuardProps {
  children: React.ReactNode
}

export default function RouteGuard({ children }: RouteGuardProps) {
  const session = useSelector((state: RootState) => state.session)
  const navigate = useNavigate()
  const { pathname } = useLocation()
  useEffect(() => {
    // 正在取得会话信息
    if (null === session) {
      return
    }
    // 若是已登录用户访问登录页面，则重定向到首页
    if (null !== session.user && LOGIN_PATHNAME === pathname) {
      navigate(HOME_PATHNAME)
    }
    // 若是未登录用户访问非登录页面，则重定向到登录页面
    if (null === session.user && LOGIN_PATHNAME !== pathname) {
      navigate(LOGIN_PATHNAME)
    }
  }, [session, pathname, navigate])
  // 正在取得会话信息
  if (null === session) {
    return <Loading />
  } else {
    return <>{children}</>
  }
}
