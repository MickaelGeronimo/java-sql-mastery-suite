# 🗄️ Enterprise SQL Curriculum & Relational Engineering Lab

This directory houses a comprehensive, production-ready curriculum of **91 SQL scripts** covering MySQL 8.0 / PostgreSQL ANSI SQL. It bridges pure database query engines with application-layer design patterns implemented in Java 17.

---

## 📂 Curriculum Architecture

```
sql/
├── 01-filtering-and-sorting/
│   └── capitulo_16_limit_final.sql
├── 02-inner-joins/
│   ├── capitulo_17_inner_joins*.sql
│   ├── capitulo_18_joining_across_databases.sql
│   ├── capitulo_19_self_joins*.sql
│   ├── capitulo_20_joining_multiple_tables*.sql
│   ├── capitulo_21_compound_join_conditions*.sql
│   └── capitulo_22_implicit_join_syntax*.sql
├── 03-outer-and-self-joins/
│   ├── capitulo_23_outer_joins*.sql
│   ├── capitulo_24_outer_join_multiple_tables*.sql
│   └── capitulo_25_self_outer_joins*.sql
├── 04-using-clause-natural-and-cross-joins/
│   ├── capitulo_26_the_using_clause*.sql
│   ├── capitulo_26_natural_joins*.sql
│   ├── capitulo_27_natural_joins*.sql
│   └── capitulo_28_cross_joins*.sql
├── 05-unions/
│   └── capitulo_29_unions_parte_1_to_6.sql
├── 06-table-attributes-and-ctas/
│   ├── capitulo_30_column_attributes*.sql
│   └── capitulo_34_creating_a_copy_of_a_table*.sql
├── 07-dml-operations/
│   ├── capitulo_31_inserting_a_single_row*.sql
│   ├── capitulo_32_inserting_multiple_rows*.sql
│   ├── capitulo_33_inserting_hierarchical_rows*.sql
│   ├── capitulo_35_updating_a_single_row*.sql
│   ├── capitulo_36_updating_multiple_rows*.sql
│   ├── capitulo_37_using_subqueries_in_updates*.sql
│   └── capitulo_38_deleting_rows*.sql
├── 08-database-administration-and-restore/
│   └── capitulo_39_restoring_the_databases*.sql
└── 09-aggregations-and-grouping/
    └── capitulo_40_aggregate_functions*.sql
```

---

## 🎯 Key Engineering Highlights

### 1. Relational Joins & Execution Plans
- **Multi-Table Joins & Fan-out Prevention**: Scripts demonstrate how joining 1:N and N:M tables can cause cartesian row explosions and distort aggregated metric calculations (`SUM()`, `AVG()`).
- **Compound Primary Keys**: Joining on composite business keys and handling temporal validity ranges.
- **Self-Joins**: Modeling corporate org-charts and hierarchical tree traversal using pure SQL and matching in Java recursion.

### 2. DML Concurrency & Integrity
- **Optimistic vs Pessimistic Concurrency**: SQL scripts illustrating `SELECT ... FOR UPDATE` row-level locks alongside application `@Version` optimistic checks.
- **Safe Update Safeguards**: Emulating and handling MySQL Error 1175 (`SQL_SAFE_UPDATES`) when attempting bulk mutations without primary key indexing.
- **Hierarchical Inserts**: Extracting generated IDs (`LAST_INSERT_ID()`) to link parent orders with multi-row child line-items.

### 3. Aggregation & Analytical Projections
- **Windowing & Grouping Pitfalls**: Understanding why scalar projection without `GROUP BY` fails in ANSI SQL engines, and how modern Java Stream collectors map 1:1 to SQL aggregation semantics.
- **Defensive Null Handling**: Utilizing `COALESCE(SUM(amount), 0)` to guarantee safe scalar returns on empty sets.
