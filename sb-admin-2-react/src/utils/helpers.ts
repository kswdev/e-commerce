export function formatDate(dateStr: string): string {
  return new Date(dateStr).toLocaleDateString("ko-KR", {
    year: "numeric",
    month: "long",
    day: "numeric",
  });
}

export function getStatusStyle(status: string): string {
  const styles: Record<string, string> = {
    active:   "status-badge status-active",
    inactive: "status-badge status-inactive",
    pending:  "status-badge status-pending",
  };
  return styles[status] ?? "status-badge";
}

export function getStatusLabel(status: string): string {
  const labels: Record<string, string> = {
    active:   "활성",
    inactive: "비활성",
    pending:  "대기",
  };
  return labels[status] ?? status;
}

export function getScoreColor(score: number): string {
  if (score >= 90) return "score-high";
  if (score >= 70) return "score-mid";
  return "score-low";
}

export function formatTimestamp(ts: number): string {
  return new Date(ts).toLocaleDateString("ko-KR", {
    year: "numeric",
    month: "long",
    day: "numeric",
  });
}

export function getOrderStatusStyle(status: string): string {
  const styles: Record<string, string> = {
    주문완료: "status-badge status-pending",
    배송중:   "status-badge status-active",
    배송완료: "status-badge score-high",
    취소:     "status-badge status-inactive",
    환불:     "status-badge status-inactive",
  };
  return styles[status] ?? "status-badge";
}

export function formatPrice(price: number): string {
  return price.toLocaleString("ko-KR") + "원";
}
