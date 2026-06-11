import {ColumnDef, flexRender} from "@tanstack/react-table";
import {useDataTable} from "../../hooks/useDataTable.ts";

const DataTable = <T,>({
    data,
    columns,
    onRowClick,
} : {
    data: T[];
    columns: ColumnDef<T, any>[];
    onRowClick?: (row: T) => void;
}) => {
    const {table, globalFilter, setGlobalFilter} = useDataTable({
        data,
        columns,
        pageSize: 5,
    });

    const {pageIndex, pageSize} = table.getState().pagination;
    const totalRows = table.getFilteredRowModel().rows.length;

    // @ts-ignore
    // @ts-ignore
    return (
        <div className="table-wrapper">
            {/* 검색 바 */}
            <div className="table-toolbar">
                <input
                    className="search-input"
                    value={globalFilter}
                    onChange={(e) => setGlobalFilter(e.target.value)}
                />
            </div>

            {/* 테이블 */}
            <div className="table-scroll">
                <table className="data-table">
                    <thead>
                    {table.getHeaderGroups().map((headerGroup) => (
                        <tr key={headerGroup.id}>
                            {headerGroup.headers.map((header) => (
                                <th
                                    key={header.id}
                                    onClick={header.column.getToggleSortingHandler()}
                                    className={header.column.getCanSort() ? "sortable" : ""}
                                >
                                    {flexRender(header.column.columnDef.header, header.getContext())}
                                    {header.column.getIsSorted() === "asc" && " ▲"}
                                    {header.column.getIsSorted() === "desc" && " ▼"}
                                    {!header.column.getIsSorted() && header.column.getCanSort() && (
                                        <span className="sort-icon"> ⇅</span>
                                    )}
                                </th>
                            ))}
                        </tr>
                    ))}
                    </thead>
                    <tbody>
                    {table.getRowModel().rows.length === 0 ? (
                        <tr>
                            <td colSpan={columns.length} className="empty-cell">
                                검색 결과가 없습니다.
                            </td>
                        </tr>
                    ) : (
                        table.getRowModel().rows.map((row) => (
                            <tr key={row.id}
                                onClick={() => onRowClick?.(row.original)}
                                className="data-row">
                                {row.getVisibleCells().map((cell) => (
                                    <td key={cell.id}>
                                        {flexRender(cell.column.columnDef.cell, cell.getContext())}
                                    </td>
                                ))}
                            </tr>
                        ))
                    )}
                    </tbody>
                </table>
            </div>

            {/* 페이지네이션 */}
            <div className="pagination">
                <span className="pagination-info">
                    총 {totalRows}명 중 {pageIndex * pageSize + 1}–{Math.min((pageIndex + 1) * pageSize, totalRows)}
                </span>
                <div className="pagination-controls">
                    <button onClick={() => table.setPageIndex(0)} disabled={!table.getCanPreviousPage()}>«</button>
                    <button onClick={() => table.previousPage()} disabled={!table.getCanPreviousPage()}>‹</button>
                    <span className="page-indicator">{pageIndex + 1} / {table.getPageCount()}</span>
                    <button onClick={() => table.nextPage()} disabled={!table.getCanNextPage()}>›</button>
                    <button onClick={() => table.setPageIndex(table.getPageCount() - 1)}
                            disabled={!table.getCanNextPage()}>»
                    </button>
                </div>
                <select
                    value={pageSize}
                    onChange={(e) => table.setPageSize(Number(e.target.value))}
                    className="page-size-select"
                >
                    {[5, 10, 15].map((size) => (
                        <option key={size} value={size}>{size}개씩 보기</option>
                    ))}
                </select>
            </div>
        </div>
    );
}

export default DataTable;