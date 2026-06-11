import React from 'react';

interface Props {
  title?: string;
}

const PageHeading: React.FC<Props> = ({ title = '' }) => (
  <div className="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 className="h3 mb-0 text-gray-800">{title}</h1>
  </div>
);

export default PageHeading;
