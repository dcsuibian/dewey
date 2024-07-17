import styles from './Special.module.scss'
import { Card } from 'antd'
import DocPreview from '@/components/DocPreview'
import DocTitle from '@/components/DocTitle'
import { getNewestDocuments } from '@/apis/server/document'

export default async function Special() {
  const { result } = await getNewestDocuments(1, 11)
  const docs = result.data
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
