import { ColumnDef, createColumnHelper } from "@tanstack/react-table";
import { User } from "../../types";
import { formatDate, getStatusStyle, getStatusLabel, getScoreColor } from "../../utils/helpers.ts";

const columnHelper = createColumnHelper<User>();

export const userColumns: ColumnDef<User, any>[] = [
  columnHelper.accessor("id", {
    header: "ID",
    cell: (info) => `#${info.getValue()}`,
    size: 60,
  }),
  columnHelper.accessor("name", {
    header: "이름",
    cell: (info) => <strong>{info.getValue()}</strong>,
  }),
  columnHelper.accessor("email", {
    header: "이메일",
    cell: (info) => <span className="email-cell">{info.getValue()}</span>,
  }),
  columnHelper.accessor("role", {
    header: "역할",
  }),
  columnHelper.accessor("status", {
    header: "상태",
    cell: (info) => (
      <span className={getStatusStyle(info.getValue())}>
        {getStatusLabel(info.getValue())}
      </span>
    ),
  }),
  columnHelper.accessor("joinDate", {
    header: "가입일",
    cell: (info) => formatDate(info.getValue()),
  }),
  columnHelper.accessor("score", {
    header: "점수",
    cell: (info) => (
      <span className={`score-badge ${getScoreColor(info.getValue())}`}>
        {info.getValue()}
      </span>
    ),
  }),
];
