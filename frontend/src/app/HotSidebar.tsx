import styles from './HotSidebar.module.scss'
import { Card } from 'antd'
import DocTitle from '@/components/DocTitle'
import { getNewestDocuments } from '@/apis/server/document'

export default async function HotSidebar() {
  const { result } = await getNewestDocuments(1, 15)
  const docs = result.data
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
