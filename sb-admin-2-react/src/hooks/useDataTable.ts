import { useMemo, useState } from "react";
import {
    ColumnDef,
    getCoreRowModel,
    getFilteredRowModel,
    getPaginationRowModel,
    getSortedRowModel,
    PaginationState,
    SortingState,
    useReactTable,
} from "@tanstack/react-table";

interface ServerPagination {
    pageIndex: number;
    pageSize: number;
    pageCount: number;
    onPaginationChange: (page: number, size: number) => void;
}

interface UseDataTableOptions<T> {
    data: T[];
    columns: ColumnDef<T, any>[];
    pageSize?: number;
    serverPagination?: ServerPagination;
}

export function useDataTable<T>({ data, columns, pageSize = 5, serverPagination }: UseDataTableOptions<T>) {
    const [sorting, setSorting] = useState<SortingState>([]);
    const [globalFilter, setGlobalFilter] = useState("");

    const memoColumns = useMemo(() => columns, []);
    const memoData = useMemo(() => data, [data]);

    const table = useReactTable({
        data: memoData,
        columns: memoColumns,
        state: {
            sorting,
            globalFilter,
            ...(serverPagination && {
                pagination: {
                    pageIndex: serverPagination.pageIndex,
                    pageSize: serverPagination.pageSize,
                },
            }),
        },
        onSortingChange: setSorting,
        onGlobalFilterChange: setGlobalFilter,
        ...(serverPagination
            ? {
                  manualPagination: true,
                  pageCount: serverPagination.pageCount,
                  onPaginationChange: (updater) => {
                      const current: PaginationState = {
                          pageIndex: serverPagination.pageIndex,
                          pageSize: serverPagination.pageSize,
                      };
                      const next = typeof updater === "function" ? updater(current) : updater;
                      serverPagination.onPaginationChange(next.pageIndex, next.pageSize);
                  },
              }
            : {
                  initialState: { pagination: { pageSize } },
              }),
        getCoreRowModel: getCoreRowModel(),
        getSortedRowModel: getSortedRowModel(),
        getFilteredRowModel: getFilteredRowModel(),
        getPaginationRowModel: getPaginationRowModel(),
    });

    return { table, globalFilter, setGlobalFilter };
}
