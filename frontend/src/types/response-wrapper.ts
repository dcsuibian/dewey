interface ResponseWrapper<T> {
  result: T
  message: string
  code: number
  timestamp: number
}

export type { ResponseWrapper }
