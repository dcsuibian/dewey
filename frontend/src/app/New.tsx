import styles from './New.module.scss'
import { Card } from 'antd'
import { Doc } from '@/types'
import DocPreview from '@/components/DocPreview'
import DocTitle from '@/components/DocTitle'

const docs: Doc[] = []
for (let i = 1; i <= 24; i++) {
  docs.push({
    id: i,
    title: '最新文章' + i,
    cover: '/cover-sample.png',
  })
}

export default function Hot() {
  return (
    <div className={styles.new}>
      <Card>
        <div className={styles.title}>
          <span>最新</span>
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
        <div className={styles.grid}>
          {docs.slice(12).map(doc => (
            <DocTitle doc={doc} />
          ))}
        </div>
      </Card>
    </div>
  )
}
