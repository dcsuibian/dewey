interface Otp {
  type: 'sms'
  receiver: string
  purpose: 'register' | 'login'
}

export type { Otp }
