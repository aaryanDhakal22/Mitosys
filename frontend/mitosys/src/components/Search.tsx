import React from "react";

const Search = ({ searchHandle }: { searchHandle: any }) => {
  const handleSearch = (event: any) => {
    searchHandle.bind(null, event.target.value)();
  };
  return (
    <>
      <input type="text" name="searchTerm" onChange={handleSearch} />
    </>
  );
};

export default Search;
