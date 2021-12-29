import React from "react";
import { useHistory } from "react-router-dom"

import TestResults from "./TestResults";


function ResultsHeader() {
  const history = useHistory()

  let transformerName = history?.location?.state?.transformerName;

  return (
    <React.Fragment>
      <TestResults transformerName={transformerName} />
    </React.Fragment>
  );
}

export default ResultsHeader;
