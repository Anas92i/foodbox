import { useEffect, useState } from "react";
import api from "../api/api";
import ReviewForm from "../components/ReviewForm";

export default function Reviews() {
  const [reviews, setReviews] = useState([]);

  const load = async () => {
    const res = await api.get("/reviews");
    setReviews(res.data);
  };

  useEffect(() => { load(); }, []);

  return (
    <div>
      <h1>Reviews</h1>
      <ReviewForm onCreated={load} />
      <ul>
        {reviews.map(r => (
          <li key={r.id}>⭐ {r.rating} — {r.comment}</li>
        ))}
      </ul>
    </div>
  );
}
