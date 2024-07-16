import {
  BranchesOutlined,
  DownloadOutlined,
  FundProjectionScreenOutlined,
  MessageOutlined,
  MoneyCollectOutlined,
  OrderedListOutlined,
  SettingOutlined,
  StarOutlined,
  StockOutlined,
  UploadOutlined,
  UserOutlined,
} from '@ant-design/icons'
import Layout from '@/components/Layout'
import Login from '@/pages/Login'
import React from 'react'
import NotFound from '@/pages/NotFound'
import Register from '@/pages/Register'

export type Route = {
  path: string
  element?: React.ReactNode
  children?: Route[]
  name?: string
  icon?: React.ReactNode
}
export const HOME_PATHNAME = '/'
export const REGISTER_PATHNAME = '/register'
export const LOGIN_PATHNAME = '/login'

const originalRoutes: Route[] = [
  {
    path: '/',
    element: <Layout />,
    children: [
      {
        name: '个人中心',
        icon: <UserOutlined />,
        path: 'user-center',
      },
      {
        name: '我的下载',
        icon: <DownloadOutlined />,
        path: 'my-download',
      },
      {
        name: '我的上传',
        icon: <UploadOutlined />,
        path: 'my-upload',
      },
      {
        name: '我的收藏',
        icon: <StarOutlined />,
        path: 'my-favorites',
      },
      {
        name: '我的订单',
        icon: <OrderedListOutlined />,
        path: 'my-order',
      },
      {
        name: '收支明细',
        icon: <MoneyCollectOutlined />,
        path: 'transaction-detail',
      },
      {
        name: '积分明细',
        icon: <FundProjectionScreenOutlined />,
        path: 'points-detail',
      },
      {
        name: '浏览记录',
        icon: <BranchesOutlined />,
        path: 'browse-record',
      },
      {
        name: '推广',
        icon: <StockOutlined />,
        path: 'promotion',
      },
      {
        name: '我的消息',
        icon: <MessageOutlined />,
        path: 'my-message',
      },
      {
        name: '账号设置',
        icon: <SettingOutlined />,
        path: 'account-setting',
      },
    ],
  },
  {
    path: '/register',
    element: <Register />,
  },
  {
    path: '/login',
    element: <Login />,
  },
  {
    path: '*',
    element: <NotFound />,
  },
]

function processRoute(routes: Route[]): Route[] {
  const result: Route[] = []
  for (const route of routes) {
    const newRoute = { ...route }
    if (newRoute.children) {
      newRoute.children = processRoute(newRoute.children)
    }
    result.push(newRoute)
  }
  return result
}

const routes = processRoute(originalRoutes)
export default routes
