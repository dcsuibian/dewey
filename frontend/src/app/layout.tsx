import type { Metadata } from 'next'
import React from 'react'
import { AntdRegistry } from '@ant-design/nextjs-registry'
import './globals.scss'
import Header from '@/components/Header'

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
        <AntdRegistry>
          <Header />
          {children}
        </AntdRegistry>
      </body>
    </html>
  )
}
