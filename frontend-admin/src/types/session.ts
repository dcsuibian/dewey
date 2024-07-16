import type { User } from './user'

interface Session {
  user: User
}

interface Login {
  type: 'phone_number_and_password' | 'phone_number_and_verification_code'
  phoneNumber: string
  password?: string
  verificationCode?: string
}

export type { Session, Login }
