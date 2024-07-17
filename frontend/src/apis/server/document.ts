import { Doc, PageWrapper, ResponseWrapper } from '@/types'

const { BACKEND_API_BASE_URL } = process.env

export async function getNewestDocuments(
  pageNumber: number,
  pageSize: number,
): Promise<ResponseWrapper<PageWrapper<Doc>>> {
  const params = new URLSearchParams({
    pageNumber: pageNumber.toString(),
    pageSize: pageSize.toString(),
  })
  const res = await fetch(`${BACKEND_API_BASE_URL}/newest-documents?` + params)
  if (!res.ok) {
    throw new Error('请求失败')
  }
  const wrapper = await res.json()
  if (200 !== wrapper.code) {
    throw new Error(wrapper.message)
  }
  return wrapper
}
