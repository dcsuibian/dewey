import { Login, ResponseWrapper, Session } from '@/types'
import sha512 from 'crypto-js/sha512'

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL
const SALT = import.meta.env.VITE_SALT

export async function getSession(): Promise<ResponseWrapper<Session>> {
  const res = await fetch(`${API_BASE_URL}/session`)
  if (!res.ok) {
    throw new Error('请求失败')
  }
  const wrapper = await res.json()
  if (200 !== wrapper.code) {
    throw new Error(wrapper.message)
  }
  return wrapper
}

export async function login(loginObj: Login): Promise<ResponseWrapper<Session>> {
  loginObj.password = sha512(`${loginObj.password}${SALT}`).toString() // 前端加盐哈希主要是为了防止后端打日志时不小心打出来用户的密码
  const res = await fetch(`${API_BASE_URL}/session`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(loginObj),
  })
  if (!res.ok) {
    throw new Error('请求失败')
  }
  const wrapper = await res.json()
  if (201 !== wrapper.code) {
    throw new Error(wrapper.message)
  }
  return wrapper
}

export async function logout(): Promise<ResponseWrapper<void>> {
  const res = await fetch(`${API_BASE_URL}/session`, {
    method: 'DELETE',
  })
  if (!res.ok) {
    throw new Error('请求失败')
  }
  const wrapper = await res.json()
  if (200 !== wrapper.code) {
    throw new Error(wrapper.message)
  }
  return wrapper
}
