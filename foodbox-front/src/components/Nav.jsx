import { Link } from "react-router-dom";

export default function Nav() {
  return (
    <nav style={{ display: "flex", gap: 12, padding: 12 }}>
      <Link to="/">Users</Link>
      <Link to="/dishes">Dishes</Link>
      <Link to="/meals">Meals</Link>
      <Link to="/reviews">Reviews</Link>
    </nav>
  );
}
