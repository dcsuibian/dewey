import Layout from '@/components/Layout'
import Login from '@/pages/Login'
import React from 'react'

export type Route = {
  path: string
  element?: React.ReactNode
  children?: Route[]
  name?: string
  icon?: React.ReactNode
}
export const HOME_PATHNAME = '/'
export const LOGIN_PATHNAME = '/login'

const originalRoutes: Route[] = [
  {
    path: '/',
    element: <Layout />,
    children: [],
  },
  {
    path: '/login',
    element: <Login />,
  },
  {
    path: '*',
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
