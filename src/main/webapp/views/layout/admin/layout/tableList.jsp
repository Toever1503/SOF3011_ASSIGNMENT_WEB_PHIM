<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="section">
	<table>
		<thead>
			<tr>
				<th>Title</th>
				<th>Count</th>
				<th>Latest Date</th>
				<th>Oldest Date</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<div class="column-title">The Garden Of Words</div>
				</td>
				<td>Slice Of School, School, Romance, Psychological</td>
				<td><span>2021/10/17 at 4:33 am</span></td>
				<td><span>2021/10/17 at 4:33 am</span></td>
			</tr>
			<tr>
				<td>
					<div class="column-title">The Garden Of Words</div>
				</td>
				<td>Slice Of School, School, Romance, Psychological</td>
				<td><span>2021/10/17 at 4:33 am</span></td>
				<td><span>2021/10/17 at 4:33 am</span></td>
			</tr>
			<tr class="page-action">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr class="page-action">
				<td></td>
				<td></td>
				<td></td>
				<td colspan="2"><span><a href="">|< </a> </span> <span
					class="pageNow" style="background: none;">5 / 20</span> <span><a
						href="">></a></span> <span><a href="">>|</a></span></td>
			</tr>
		</tbody>
	</table>
</div>

<style>
.section {
	border: 1px solid gray;
	background-color: white;
	margin: 10px 0;
}

.add-new-film {
	border: 1px solid gray;
	padding: 5px;
	border-radius: 5px;
	background-color: white;
}

.section table {
	width: 100%;
	border-collapse: collapse;
	border: 1px solid black;
	text-align: center;
}

.section table thead {
	width: 100%;
	margin: 10px 0;
	border: 1px solid gray;
}

.section table tbody {
	width: 100%;
	margin: 10px 0;
	border: 1px solid gray;
}

.section table tr {
	line-height: 35px;
}

.section table tbody tr:nth-child(odd) {
	background: #fff0f0;
}

.section table td:nth-child(1) input {
	display: block;
	margin: 0 auto;
}

.section table td:nth-child(2), .section table td:nth-child(3), .section table td:nth-child(4),
	.section table td:nth-child(5) {
	width: 300px;
}

.column-action {
	font-size: 12px;
	height: 30px;
}

.column-action span {
	display: none;
	transition: 0.4s ease-in-out;
}

.section table td:nth-child(2):hover .column-action>span {
	display: inline;
}

.column-action span:nth-child(2) {
	border-left: 1px solid gray;
	border-right: 1px solid gray;
	padding: 2px;
}

.column-action span:hover a {
	color: teal;
}

.section table tbody tr.page-action {
	line-height: unset;
}

.section table tbody tr.page-action span {
	display: inline-block;
	padding: 5px;
	border: 1px solid gainsboro;
	font-weight: bold;
	transition: 0.3s ease-in-out;
	cursor: pointer;
}

.section table tbody tr.page-action span:hover {
	background-color: azure;
}
</style>