import styles from './index.module.scss'

export default function Header() {
  return (
    <header className={styles.header}>
      <div>
        <nav>
          <div className={styles.logo}>Dewey文库</div>
          <a>首页</a>
          <a>分类</a>
          <a>代理入驻</a>
          <a>开通会员</a>
          <a>我要上传</a>
          <a>登录/注册</a>
        </nav>
      </div>
    </header>
  )
}
