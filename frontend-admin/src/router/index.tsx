import type { RouteObject } from 'react-router'
import { createBrowserRouter } from 'react-router-dom'
import routes, { Route } from '@/config/routes.tsx'
import RouteGuard from './RouteGuard.tsx'

function convertToReactRouterFormat(routes: Route[]): RouteObject[] {
  const result: RouteObject[] = []
  for (const route of routes) {
    const newRouteObject: RouteObject = { ...route }
    if (route.element) {
      newRouteObject.element = <RouteGuard>{route.element}</RouteGuard>
    }
    if (route.children) {
      newRouteObject.children = convertToReactRouterFormat(route.children)
    }
    result.push(newRouteObject)
  }
  return result
}

const router = createBrowserRouter(convertToReactRouterFormat(routes))
export default router
