import { Register, ResponseWrapper } from '@/types'

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

export async function register(register: Register): Promise<ResponseWrapper<void>> {
  const response = await fetch(`${API_BASE_URL}/users`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(register),
  })
  return response.json()
}
