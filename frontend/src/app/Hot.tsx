import styles from './Hot.module.scss'
import { Card } from 'antd'
import DocPreview from '@/components/DocPreview'
import { getNewestDocuments } from '@/apis/server/document'

export default async function Hot() {
  const { result } = await getNewestDocuments(1, 18)
  const docs = result.data
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
