import styles from './Hot.module.scss'
import { Card } from 'antd'
import { Doc } from '@/types'
import DocPreview from '@/components/DocPreview'

const docs: Doc[] = []
for (let i = 1; i <= 18; i++) {
  docs.push({
    id: i,
    title: '热门文章' + i,
    cover: '/cover-sample.png',
  })
}

export default function Hot() {
  return (
    <div className={styles.hot}>
      <Card>
        <div className={styles.title}>
          <span>热门</span>
          <span>查看更多</span>
        </div>
        <div className={styles.line}>
          {docs.slice(0, 6).map(doc => (
            <div className={styles.item}>
              <DocPreview doc={doc} />
            </div>
          ))}
        </div>
        <div className={styles.line}>
          {docs.slice(6, 12).map(doc => (
            <div className={styles.item}>
              <DocPreview doc={doc} />
            </div>
          ))}
        </div>
        <div className={styles.line}>
          {docs.slice(12, 18).map(doc => (
            <div className={styles.item}>
              <DocPreview doc={doc} />
            </div>
          ))}
        </div>
      </Card>
    </div>
  )
}
