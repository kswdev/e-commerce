import React from 'react';

interface Props {
    categories: string[];
    selected: string;
    onSelect: (category: string) => void;
}

const CategoryFilter = ({ categories, selected, onSelect }: Props) => {
    return (
        <ul className="nav nav-pills mb-4">
            {categories.map(cat => (
                <li className="nav-item" key={cat}>
                    <button
                        className={`nav-link${selected === cat ? ' active' : ''}`}
                        onClick={() => onSelect(cat)}
                    >
                        {cat}
                    </button>
                </li>
            ))}
        </ul>
    );
};

export default CategoryFilter;
