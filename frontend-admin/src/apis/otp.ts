import { Otp, ResponseWrapper } from '@/types'

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

export async function sendOtp(otp: Otp): Promise<ResponseWrapper<void>> {
  const res = await fetch(`${API_BASE_URL}/otp`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(otp),
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
