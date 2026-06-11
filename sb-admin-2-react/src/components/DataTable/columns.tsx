import { ColumnDef, createColumnHelper } from "@tanstack/react-table";
import {Order, Product, User} from "../../types";
import { formatDate, getStatusStyle, getStatusLabel, getScoreColor, formatTimestamp, getOrderStatusStyle, formatPrice } from "../../utils/helpers.ts";

const userColumnHelper = createColumnHelper<User>();

export const userColumns: ColumnDef<User, any>[] = [
  userColumnHelper.accessor("id", {
    header: "ID",
    cell: (info) => `#${info.getValue()}`,
    size: 60,
  }),
  userColumnHelper.accessor("name", {
    header: "이름",
    cell: (info) => <strong>{info.getValue()}</strong>,
  }),
  userColumnHelper.accessor("email", {
    header: "이메일",
    cell: (info) => <span className="email-cell">{info.getValue()}</span>,
  }),
  userColumnHelper.accessor("role", {
    header: "역할",
  }),
  userColumnHelper.accessor("status", {
    header: "상태",
    cell: (info) => (
      <span className={getStatusStyle(info.getValue())}>
        {getStatusLabel(info.getValue())}
      </span>
    ),
  }),
  userColumnHelper.accessor("joinDate", {
    header: "가입일",
    cell: (info) => formatDate(info.getValue()),
  }),
  userColumnHelper.accessor("score", {
    header: "점수",
    cell: (info) => (
      <span className={`score-badge ${getScoreColor(info.getValue())}`}>
        {info.getValue()}
      </span>
    ),
  }),
];

const productColumnHelper = createColumnHelper<Product>();

export const productColumns: ColumnDef<Product, any>[] = [
  productColumnHelper.accessor("id", {
    header: "ID",
    cell: (info) => `#${info.getValue()}`,
    size: 60,
  }),
  productColumnHelper.accessor("name", {
    header: "이름",
    cell: (info) => info.getValue(),
  }),
  productColumnHelper.accessor("price", {
    header: "가격",
    cell: (info) => info.getValue(),
  }),
  productColumnHelper.accessor("company", {
    header: "업체",
    cell: (info) => info.getValue(),
  }),
  productColumnHelper.accessor("createdAt", {
    header: "등록일",
    cell: (info) => formatDate(info.getValue()),
  }),
  productColumnHelper.accessor("remnant", {
    header: "재고",
    cell: (info) => info.getValue(),
  }),
];

const orderColumnHelper = createColumnHelper<Order>();

export const orderColumns: ColumnDef<Order, any>[] = [
  orderColumnHelper.accessor("id", {
    header: "ID",
    cell: (info) => `#${info.getValue()}`,
    size: 60,
  }),
  orderColumnHelper.accessor("orderer", {
    header: "주문자",
    cell: (info) => <strong>{info.getValue()}</strong>,
  }),
  orderColumnHelper.accessor("price", {
    header: "결제금액",
    cell: (info) => formatPrice(info.getValue()),
  }),
  orderColumnHelper.accessor("status", {
    header: "상태",
    cell: (info) => (
      <span className={getOrderStatusStyle(info.getValue())}>
        {info.getValue()}
      </span>
    ),
  }),
  orderColumnHelper.accessor("paymentMethod", {
    header: "결제수단",
    cell: (info) => info.getValue(),
  }),
  orderColumnHelper.accessor("orderDate", {
    header: "주문일",
    cell: (info) => formatTimestamp(info.getValue()),
  }),
];