import { useEffect, useState } from "react";
import api from "../api/api";
import MealForm from "../components/MealForm";

export default function Meals() {
  const [meals, setMeals] = useState([]);

  const load = async () => {
    const res = await api.get("/meals");
    setMeals(res.data);
  };

  useEffect(() => { load(); }, []);

  return (
    <div>
      <h1>Meals</h1>
      <MealForm onCreated={load} />
      <ul>
        {meals.map(m => (
          <li key={m.id}>
            user:{m.user?.username} — dish:{m.dish?.name}
          </li>
        ))}
      </ul>
    </div>
  );
}
