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
