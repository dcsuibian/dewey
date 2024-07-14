import { ProLayout } from '@ant-design/pro-components'
import { Link, Outlet, useLocation, useNavigate } from 'react-router-dom'
import routes, { LOGIN_PATHNAME } from '@/config/routes'
import { useDispatch, useSelector } from 'react-redux'
import { AppDispatch, RootState } from '@/store'
import { LogoutOutlined } from '@ant-design/icons'
import { Dropdown } from 'antd'
import { getSession, logout } from '@/store/modules/session.ts'

export default function Layout() {
  const dispatch = useDispatch<AppDispatch>()
  const location = useLocation()
  const navigate = useNavigate()
  const session = useSelector((state: RootState) => state.session)
  const onLogoutClick = async () => {
    await dispatch(logout())
    navigate(LOGIN_PATHNAME)
    await dispatch(getSession())
  }
  return (
    <ProLayout
      title="Dewey文库管理系统"
      layout="mix"
      route={routes[0]}
      location={location}
      avatarProps={{
        title: session.user?.name,
        src: session.user?.avatar,
        render: (_, dom) => {
          return (
            <Dropdown
              menu={{
                items: [
                  {
                    key: 'logout',
                    icon: <LogoutOutlined />,
                    label: '退出登录',
                    onClick: onLogoutClick,
                  },
                ],
              }}
            >
              {dom}
            </Dropdown>
          )
        },
      }}
      menuItemRender={(itemProps, defaultDom) => {
        if (itemProps.isUrl || itemProps.children) {
          return defaultDom
        }
        if (itemProps.path && location.pathname !== itemProps.path) {
          return (
            <Link to={itemProps.path} target={itemProps.target}>
              {defaultDom}
            </Link>
          )
        }
        return defaultDom
      }}
    >
      <Outlet />
    </ProLayout>
  )
}
