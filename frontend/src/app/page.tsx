import Search from './Search'
import styles from './page.module.scss'
import Hot from './Hot'
import New from './New'
import HotSidebar from './HotSidebar'
import Special from './Special'

export const revalidate = 60 // 1分钟更新一次
export default function Home() {
  return (
    <main className={styles.main}>
      <div className={styles.search}>
        <Search />
      </div>
      <div className={styles.content}>
        <div className={styles.left}>
          <div className={styles.new}>
            <New />
          </div>
          <div className={styles.hot}>
            <Hot />
          </div>
          <div className={styles.special}>
            <Special />
          </div>
        </div>
        <div className={styles.right}>
          <div>
            <HotSidebar />
          </div>
        </div>
      </div>
    </main>
  )
}
