import type { Metadata } from 'next'
import React from 'react'
import { AntdRegistry } from '@ant-design/nextjs-registry'
import './globals.scss'

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
      <body>
        <AntdRegistry>{children}</AntdRegistry>
      </body>
    </html>
  )
}
