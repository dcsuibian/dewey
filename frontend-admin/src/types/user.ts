interface User {
  id: number
  name: string
  password: string
  phoneNumber: string
  avatar: string
  email: string
  gender: '男' | '女' | '保密'
}

interface Register {
  type: 'phone_number_and_verification_code'
  phoneNumber: string
  verificationCode: string
}

export type { User, Register }
