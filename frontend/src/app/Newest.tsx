import styles from './Newest.module.scss'
import { Card } from 'antd'
import DocPreview from '@/components/DocPreview'
import DocTitle from '@/components/DocTitle'
import { getNewestDocuments } from '@/apis/server/document'

export default async function Hot() {
  const { result } = await getNewestDocuments(1, 24)
  const docs = result.data
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
