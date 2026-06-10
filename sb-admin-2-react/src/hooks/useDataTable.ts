import { useState, useMemo } from "react";
import {
    useReactTable,
    getCoreRowModel,
    getSortedRowModel,
    getFilteredRowModel,
    getPaginationRowModel,
    SortingState,
    ColumnDef,
} from "@tanstack/react-table";

interface UseDataTableOptions<T> {
    data: T[];
    columns: ColumnDef<T, any>[];
    pageSize?: number;
}

export function useDataTable<T>({ data, columns, pageSize = 5 }: UseDataTableOptions<T>) {
    const [sorting, setSorting] = useState<SortingState>([]);
    const [globalFilter, setGlobalFilter] = useState("");

    const memoColumns = useMemo(() => columns, []);
    const memoData = useMemo(() => data, [data]);

    const table = useReactTable({
        data: memoData,
        columns: memoColumns,
        state: { sorting, globalFilter },
        onSortingChange: setSorting,
        onGlobalFilterChange: setGlobalFilter,
        getCoreRowModel: getCoreRowModel(),
        getSortedRowModel: getSortedRowModel(),
        getFilteredRowModel: getFilteredRowModel(),
        getPaginationRowModel: getPaginationRowModel(),
        initialState: { pagination: { pageSize } },
    });

    return { table, globalFilter, setGlobalFilter };
}
