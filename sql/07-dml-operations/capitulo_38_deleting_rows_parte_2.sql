-- ==============================================================================
-- CAPÍTULO 38 (PARTE 2): INTEGRIDADE REFERENCIAL, ERRO 1451 E SOFT DELETE
-- Chaves Estrangeiras, O Erro 1451 (Cannot delete or update a parent row),
-- Ações ON DELETE (RESTRICT, CASCADE, SET NULL) e o Padrão Corporativo de Soft Delete
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 10 (Aprofundamento Corporativo)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_38_deleting_rows_parte_2.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. O PESADELO DA INTEGRIDADE REFERENCIAL: O ERRO 1451 DO MYSQL
-- ------------------------------------------------------------------------------
-- O cliente de ID 2 ('Myworks') possui faturas atreladas a ele na tabela 'invoices'.
-- O que acontece se tentarmos apagar o cliente 'Myworks' diretamente da tabela 'clients'?
-- ------------------------------------------------------------------------------

-- Se você executar o comando abaixo:
-- DELETE FROM clients WHERE client_id = 2;
--
-- O motor InnoDB bloqueia imediatamente a destruição física e dispara:
-- -> ERROR 1451 (23000): Cannot delete or update a parent row: a foreign key constraint fails 
--    (`sql_invoicing`.`invoices`, CONSTRAINT `fk_invoices_clients` FOREIGN KEY (`client_id`) 
--     REFERENCES `clients` (`client_id`) ON DELETE RESTRICT ON UPDATE CASCADE)
--
-- MOTIVO: O MySQL protege contra 'Registros Órfãos' (Orphan Rows). Se o cliente fosse
-- apagado, as faturas dele ficariam apontando para o nada!
-- ------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------
-- 2. AS 3 OPÇÕES DE CONFIGURAÇÃO DE UMA FOREIGN KEY (ON DELETE)
-- ------------------------------------------------------------------------------
-- Ao criar uma Chave Estrangeira, o arquiteto de banco de dados escolhe uma destas 3 regras:
--
-- 1. ON DELETE RESTRICT (ou NO ACTION - Padrão do MySQL):
--    Se existirem faturas filhas, a exclusão do cliente pai é SUMARIAMENTE BLOQUEADA com Erro 1451.
--
-- 2. ON DELETE CASCADE:
--    Ao apagar o cliente pai, o MySQL apaga AUTOMATICAMENTE todas as suas faturas filhas em cascata!
--    (CUIDADO: Em tabelas financeiras, isso pode destruir histórico contábil de milhões de reais!)
--
-- 3. ON DELETE SET NULL:
--    Ao apagar o cliente pai, o MySQL mantém as faturas, mas define a coluna 'client_id = NULL'.
-- ------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------
-- 3. O PADRÃO CORPORATIVO SENIOR: SOFT DELETE (EXCLUSÃO LÓGICA)
-- ------------------------------------------------------------------------------
-- Em bancos de dados de produção (fintechs, hospitais, e-commerces), o comando
-- físico 'DELETE FROM' é TERMINANTEMENTE PROIBIDO por compliance contábil e auditoria (LGPD/SOX).
--
-- Em vez de destruir a tupla física, marcamos a linha como excluída através
-- de colunas de auditoria: 'is_deleted' e 'deleted_at':
-- ------------------------------------------------------------------------------

-- Passo 3.1: Adicionando colunas de Soft Delete na tabela clients (se não existirem):
ALTER TABLE clients 
ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE,
ADD COLUMN deleted_at DATETIME NULL;

-- Passo 3.2: A "Exclusão" Lógica vira um UPDATE atômico:
UPDATE clients
SET 
    is_deleted = TRUE,
    deleted_at = NOW()
WHERE client_id = 2;

-- Passo 3.3: Como as consultas da aplicação passam a buscar os clientes ativos:
SELECT client_id, name, city, state, is_deleted, deleted_at
FROM clients
WHERE is_deleted = FALSE;

-- Passo 3.4: Como o time de Compliance e Auditoria Fiscal consulta clientes inativados:
SELECT client_id, name, is_deleted, deleted_at
FROM clients
WHERE is_deleted = TRUE;

-- ------------------------------------------------------------------------------
-- 4. RESTAURANDO O CLIENTE 2 (UNDELETE / ROLLBACK LÓGICO)
-- ------------------------------------------------------------------------------
-- Veja a mágica do Soft Delete: se a exclusão foi um engano do operador humano,
-- a recuperação é instantânea sem precisar de backups ou restauração de dump:
-- ------------------------------------------------------------------------------

UPDATE clients
SET 
    is_deleted = FALSE,
    deleted_at = NULL
WHERE client_id = 2;

SELECT client_id, name, is_deleted, deleted_at
FROM clients
WHERE client_id = 2;
