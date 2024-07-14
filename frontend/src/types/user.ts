interface User {
  id: number
  name: string
  password: string
  phoneNumber: string
  avatar: string
  email: string
  gender: '男' | '女' | '保密'
}

export type { User }
