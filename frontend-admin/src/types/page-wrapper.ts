interface PageWrapper<T> {
  data: Array<T>
  pageNumber: number
  pageSize: number
  total: number
  totalPages: number
}

export type { PageWrapper }
