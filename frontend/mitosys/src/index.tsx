import React from "react";
import * as ReactDOMClient from "react-dom/client";
import "./index.css";
import App from "./App";

const container: HTMLElement = document.getElementById("app") as HTMLElement;

// Create a root.
const root = ReactDOMClient.createRoot(container);

// Initial render: Render an element to the root.
root.render(<App />);

