import { Doc } from '@/types'

export default function DocTitle({ doc }: { doc: Doc }) {
  return <div>{doc.title}</div>
}
