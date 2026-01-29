import { BrowserRouter, Routes, Route } from "react-router-dom";
import Nav from "./components/Nav";
import Users from "./pages/Users";
import Dishes from "./pages/Dishes";
import Meals from "./pages/Meals";
import Reviews from "./pages/Reviews";

export default function App() {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="/" element={<Users />} />
        <Route path="/dishes" element={<Dishes />} />
        <Route path="/meals" element={<Meals />} />
        <Route path="/reviews" element={<Reviews />} />
      </Routes>
    </BrowserRouter>
  );
}
