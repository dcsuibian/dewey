import './globals.scss'
import type { Metadata } from 'next'
import React from 'react'

export const metadata: Metadata = {
  title: 'Dewey文库',
}

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html lang="zh-CN">
      <body>{children}</body>
    </html>
  )
}
