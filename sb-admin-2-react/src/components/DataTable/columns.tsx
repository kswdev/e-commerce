import { ColumnDef, createColumnHelper } from "@tanstack/react-table";
import {Customer, Order, Product} from "../../types";
import { formatDate, formatTimestamp, getOrderStatusStyle, formatPrice } from "../../utils/helpers.ts";

const customerColumnHelper = createColumnHelper<Customer>();

export const customerColumns: ColumnDef<Customer, any>[] = [
  customerColumnHelper.accessor("customerId", {
    header: "ID",
    cell: (info) => `#${info.getValue()}`,
    size: 60,
  }),
  customerColumnHelper.accessor("customerName", {
    header: "이름",
    cell: (info) => <strong>{info.getValue()}</strong>,
  }),
  customerColumnHelper.accessor("age", {
    header: "나이",
    cell: (info) => `${info.getValue()}세`,
  }),
  customerColumnHelper.accessor("phoneNumber", {
    header: "전화번호",
    cell: (info) => info.getValue(),
  }),
  customerColumnHelper.accessor("address", {
    header: "주소",
    cell: (info) => info.getValue(),
  }),
  customerColumnHelper.accessor("grade", {
    header: "등급",
    cell: (info) => (
      <span className={info.getValue() === "VIP" ? "badge badge-warning" : "badge badge-secondary"}>
        {info.getValue()}
      </span>
    ),
  }),
  customerColumnHelper.accessor("createdAt", {
    header: "가입일",
    cell: (info) => formatDate(info.getValue()),
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