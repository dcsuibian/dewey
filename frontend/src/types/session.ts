import type { User } from './user'

interface Session {
  user: User
}

type Login = {
  type: 'phone_number_and_password'
  phoneNumber: string
  password: string
}
export type { Session, Login }
