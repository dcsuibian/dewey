import styles from './Special.module.scss'
import { Card } from 'antd'
import DocPreview from '@/components/DocPreview'
import { Doc } from '@/types'
import DocTitle from '@/components/DocTitle'

const docs: Doc[] = []
for (let i = 1; i <= 11; i++) {
  docs.push({
    id: i,
    title: '党政文章' + i,
    cover: '/cover-sample.png',
  })
}
export default function Special() {
  return (
    <div className={styles.special}>
      <Card>
        <div className={styles.title}>
          <span>党政</span>
          <span>查看更多</span>
        </div>
        <div className={styles.content}>
          <div className={styles.first}>
            <DocPreview doc={docs[0]} />
          </div>
          <div className={styles.other}>
            {docs.slice(1).map(doc => (
              <DocTitle doc={doc} />
            ))}
          </div>
        </div>
      </Card>
    </div>
  )
}
