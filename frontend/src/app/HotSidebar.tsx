import styles from './HotSidebar.module.scss'
import { Card } from 'antd'
import { Doc } from '@/types'
import DocTitle from '@/components/DocTitle'

const docs: Doc[] = []
for (let i = 1; i <= 15; i++) {
  docs.push({
    id: i,
    title: '热门文章' + i,
    cover: '/cover-sample.png',
  })
}

export default function HotSidebar() {
  return (
    <div className={styles.hot}>
      <Card>
        <div className={styles.title}>
          <span>热门</span>
          <span>查看更多</span>
        </div>
        <div>
          {docs.map(doc => (
            <DocTitle doc={doc} key={doc.id} />
          ))}
        </div>
      </Card>
    </div>
  )
}
