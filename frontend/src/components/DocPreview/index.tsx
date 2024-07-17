import styles from './index.module.scss'
import { Doc } from '@/types'

export default function DocPreview({ doc }: { doc: Doc }) {
  return (
    <div className={styles.preview}>
      <div className={styles.cover}>
        <img src={doc.cover} alt={doc.title} />
      </div>
      <div className={styles.title}>{doc.title}</div>
    </div>
  )
}
