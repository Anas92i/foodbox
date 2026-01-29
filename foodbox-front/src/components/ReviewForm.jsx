import { useEffect, useState } from "react";
import api from "../api/api";

export default function ReviewForm({ onCreated }) {
  const [meals, setMeals] = useState([]);
  const [mealId, setMealId] = useState("");
  const [rating, setRating] = useState(5);
  const [comment, setComment] = useState("");

  useEffect(() => {
    api.get("/meals").then(r => setMeals(r.data));
  }, []);

  const submit = async (e) => {
    e.preventDefault();
    await api.post(`/reviews?mealId=${mealId}&rating=${rating}&comment=${comment}`);
    onCreated();
  };

  return (
    <form onSubmit={submit}>
      <select onChange={e=>setMealId(e.target.value)}>
        <option>Meal</option>
        {meals.map(m => <option key={m.id} value={m.id}>{m.id}</option>)}
      </select>

      <input type="number" min="0" max="5" value={rating} onChange={e=>setRating(e.target.value)} />
      <input placeholder="comment" value={comment} onChange={e=>setComment(e.target.value)} />
      <button>Review</button>
    </form>
  );
}
